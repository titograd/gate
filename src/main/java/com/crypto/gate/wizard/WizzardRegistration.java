package com.crypto.gate.wizard;

import org.primefaces.event.FlowEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static com.crypto.gate.rest.RestClient.postRegistrationForm;

@ManagedBean
@ViewScoped
public class WizzardRegistration {

    private boolean skip;
    private RegistrationForm registrationForm;

    public WizzardRegistration() {
        registrationForm = new RegistrationForm();
    }


    public String onFlowProcess(FlowEvent event) {

        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public void save() {
        postRegistrationForm(registrationForm);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
}
