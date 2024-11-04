package com.decrypto.challenge.controllers;

import com.decrypto.challenge.utils.TestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public abstract class ControllerTest {

    protected final static EasyRandom easyRandom = TestUtils.easyRandom();
    protected MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = standaloneSetup(this.getTarget()).setConversionService(new DefaultFormattingConversionService()).build();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    protected abstract Object getTarget();

    protected <I> void perform(final MockHttpServletRequestBuilder requestBuilder, final ResultMatcher status) throws Exception {
        this.performRequest(requestBuilder, status);
    }

    protected <I> void perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final ResultMatcher status) throws Exception {
        this.performRequest(requestBuilder, request, status);
    }

    protected <I, O> O perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final Class<O> aClass, final ResultMatcher status) throws Exception {
        final MvcResult result = performRequest(requestBuilder, request, status);
        return (aClass != null) ? objectMapper.readValue(result.getResponse().getContentAsString(), aClass) : null;
    }

    protected <I, O> O perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final TypeReference<O> typeReference, final ResultMatcher status) throws Exception {
        final MvcResult result = performRequest(requestBuilder, request, status);
        return (typeReference != null) ? objectMapper.readValue(result.getResponse().getContentAsString(), typeReference) : null;
    }

    private <I> MvcResult performRequest(final MockHttpServletRequestBuilder requestBuilder, final I request, final ResultMatcher status) throws Exception {
        final String json = objectMapper.writeValueAsString(request);
        return this.mockMvc.perform(requestBuilder.content(json).contentType(APPLICATION_JSON)).andExpect(status).andReturn();
    }

    private <I> MvcResult performRequest(final MockHttpServletRequestBuilder requestBuilder, final ResultMatcher status) throws Exception {
        return this.mockMvc.perform(requestBuilder.contentType(APPLICATION_FORM_URLENCODED_VALUE)).andExpect(status).andReturn();
    }

}
