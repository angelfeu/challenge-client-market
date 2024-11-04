package com.decrypto.challenge.controllers;

import com.decrypto.challenge.application.services.MarketService;
import com.decrypto.challenge.controllers.model.ClientResponse;
import com.decrypto.challenge.controllers.model.MarketRequest;
import com.decrypto.challenge.controllers.model.MarketResponse;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @Operation(summary = "Create a new market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MarketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public ResponseEntity<MarketResponse> createMarket(
            @NotNull @RequestBody MarketRequest marketRequest) {
        MarketDTO market = marketService.save(marketRequest);
        return ResponseEntity.ok(MarketResponse.from(market));
    }

    @Operation(summary = "Get a market for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the market",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MarketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Market not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<MarketResponse> getMarketById(@PathVariable Long id) {
        Optional<MarketDTO> market = Optional.ofNullable(marketService.getById(id));
        return market.map(value -> new ResponseEntity<>(MarketResponse.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MarketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PutMapping("/{id}")
    public ResponseEntity<MarketResponse> updateMarket(@NotNull @PathVariable Long id,
                                                       @NotNull @RequestBody MarketRequest marketRequest) {
        try {
            marketRequest.setId(id);
            MarketDTO updatedMarket = marketService.update(marketRequest);
            return new ResponseEntity<>(MarketResponse.from(updatedMarket), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a market for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the market",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        try {
            marketService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
