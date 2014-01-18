package com.crypto.gate.rest;

import com.crypto.gate.wizard.RegistrationForm;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

    public static void postRegistrationForm(RegistrationForm registrationForm) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8085/register");
            ClientResponse response = webResource.type("application/xml").post(ClientResponse.class,xml(registrationForm));
            verification(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verification(ClientResponse response) {
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
    }

    //TODO Use JAXB here so you dont have to manually create the XML
    private static String xml(RegistrationForm registrationForm) {
        String registrationXML =
                "<REGISTRATION>" +
                        "<BUSINESS_NAME>" + registrationForm.getBussinessName() + "</BUSINESS_NAME>" +
                        "<EMAIL>" + registrationForm.getEmail() + "</EMAIL>" +
                        "<FIRST_NAME>" + registrationForm.getFirstName() + "</FIRST_NAME>" +
                        "<SECOND_NAME>" + registrationForm.getFirstName() + "</SECOND_NAME>" +
                        "<PHONE_NUMBER>" + registrationForm.getPhoneNumber() + "</PHONE_NUMBER>" +
                        "<ADDRESS>" + registrationForm.getAddress() + "</ADDRESS>" +
                        "<INDUSTRY>" + registrationForm.getIndustry() + "</INDUSTRY>" +
                        "<POSTAL_CODE>" + registrationForm.getPostalcode() + "</POSTAL_CODE>" +
                        "<CITY>" + registrationForm.getCity() + "</CITY>" +
                        "<STATE>" + registrationForm.getState() + "</STATE>" +
                        "<COUNTRY>" + registrationForm.getCountry() + "</COUNTRY>" +
                        "<STORE>" + registrationForm.getStore() + "</STORE>" +
                        "<PRIMARY_WEBSITE>" + registrationForm.getPrimarywebsite() + "</PRIMARY_WEBSITE>" +
                        "<SHOPING_CART_ORR_POS_SOFTWARE>" + registrationForm.getShopingCartOrPosSoftware() + "</SHOPING_CART_ORR_POS_SOFTWARE>" +
                "</REGISTRATION>";
        return registrationXML;
    }
}
