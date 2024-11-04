package com.decrypto.challenge.controllers;

import com.decrypto.challenge.application.services.ClientService;
import com.decrypto.challenge.controllers.model.ClientMarketRequest;
import com.decrypto.challenge.controllers.model.ClientMarketResponse;
import com.decrypto.challenge.controllers.model.ClientRequest;
import com.decrypto.challenge.controllers.model.ClientResponse;
import com.decrypto.challenge.domain.model.ClientDTO;
import com.decrypto.challenge.domain.model.MarketDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@NotNull @RequestBody ClientRequest clientRequest) {
        ClientDTO clientNew = clientService.save(clientRequest);
        return ResponseEntity.ok(ClientResponse.from(clientNew));
    }

    @Operation(summary = "Get a client for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the client",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        Optional<ClientDTO> client = Optional.ofNullable(clientService.getById(id));
        return client.map(value -> new ResponseEntity<>(ClientResponse.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@NotNull @PathVariable Long id,
                                                       @NotNull @RequestBody ClientRequest clientRequest) {
        try {
            clientRequest.setId(id);
            ClientDTO updatedClient = clientService.update(clientRequest);
            return new ResponseEntity<>(ClientResponse.from(updatedClient), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a client for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the client",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a list of markets for a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the markets",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientMarketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @GetMapping("/{id}/markets")
    public ResponseEntity<ClientMarketResponse> getMarkets(@PathVariable Long id) {
        List<MarketDTO> markets = clientService.getMarkets(id);
        return new ResponseEntity<>(ClientMarketResponse.from(markets), HttpStatus.OK);
    }

    @Operation(summary = "Add markets to a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientMarketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping("/{id}/markets/add")
    public ResponseEntity<ClientMarketResponse> addMarkets(
            @PathVariable Long id,
            @NotNull @RequestBody ClientMarketRequest request) {
        List<MarketDTO> markets = clientService.addMarkets(id, request.getMarkets());
        return ResponseEntity.ok(ClientMarketResponse.from(markets));
    }

    @Operation(summary = "Remove markets to a client id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Markets removed successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @PostMapping("/{id}/markets/remove")
    public ResponseEntity<Void> removeMarkets(@PathVariable Long id,
                                              @NotNull @RequestBody ClientMarketRequest request) {
        clientService.removeMarkets(id, request.getMarkets());
        return ResponseEntity.ok().build();
    }

}
