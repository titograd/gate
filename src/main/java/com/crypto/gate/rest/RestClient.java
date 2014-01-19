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
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json(registrationForm));
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
    private static String json(RegistrationForm registrationForm) {
//TODO Remove STORE is redundant(Probalby this value exist in the database in the server side. Remove it also)
        String registrationJson = "{\n" +
                "  \"REGISTRATION\": {\n" +
                "    \"BUSINESS_NAME\": \""+registrationForm.getBussinessName()+"\",\n" +
                "    \"EMAIL\": \""+registrationForm.getEmail()+"\",\n" +
                "    \"FIRST_NAME\": \""+registrationForm.getFirstName()+"\",\n" +
                "    \"SECOND_NAME\": \""+registrationForm.getLastName()+"\",\n" +
                "    \"PHONE_NUMBER\": \""+registrationForm.getPhoneNumber()+"\",\n" +
                "    \"ADDRESS\": \""+registrationForm.getAddress()+"\",\n" +
                "    \"INDUSTRY\": \""+registrationForm.getIndustry()+"\",\n" +
                "    \"POSTAL_CODE\": \""+registrationForm.getPostalcode()+"\",\n" +
                "    \"CITY\": \""+registrationForm.getCity()+"\",\n" +
                "    \"STATE\": \""+registrationForm.getState()+"\",\n" +
                "    \"COUNTRY\": \""+registrationForm.getCountry()+"\",\n" +
                "    \"STORE\": \""+registrationForm.getStore()+"\",\n" +
                "    \"PRIMARY_WEBSITE\": \""+registrationForm.getPrimarywebsite()+"\",\n" +
                "    \"SHOPING_CART_ORR_POS_SOFTWARE\": \""+registrationForm.getShopingCartOrPosSoftware()+"\"\n" +
                "  }\n" +
                "}";
        return registrationJson;
    }
}
