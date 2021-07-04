package com.example.testscripts;

import com.example.pages.LoginPage;
import com.example.properties.Environment;

import org.assertj.core.api.Fail;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class AuthenticationTests extends TestBase {     
 
    private LoginPage loginPage;

    @Test (priority=1, description = "010: Autenticación (Despliegue de la vista de autenticación)")
    //010: Autenticación (Despliegue de la vista de autenticación)    
    public void verifyLoginPageAuthentication() {
        try {

            loginPage = new LoginPage(driver);  
            loginPage.load();
            assertThat(loginPage.getUsernameInput().isDisplayed()).isTrue();
            assertThat(loginPage.getPasswordInput().isDisplayed()).isTrue();
            assertThat(loginPage.getLoginButton().isDisplayed()).isTrue();
            assertThat(loginPage.getLogo().isDisplayed()).isTrue();
            
        }
        catch(Exception e){
            takeSnapShot("010-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }
        
    }

    @Test (priority=2, description = "002: Autenticación (usuario bloqueado)")
    //002: Autenticación (usuario bloqueado)
    public void verifyLockedUserAuthentication() {

        try {

            typeCredentials(Environment.getProperties().username_locked(),Environment.getProperties().password());

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getLockedUserMessage());
            
        }
        catch(Exception e){
            takeSnapShot("002-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }
    

    @Test (priority=3, description = "003: Autenticación (nombre de usuario vacío)")
    //003: Autenticación (nombre de usuario vacío)
    public void verifyEmptyUsernameAuthentication() {

        try {

            typeCredentials(null,Environment.getProperties().password());
        
            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getUsernameRequiredMessage());
            
        }
        catch(Exception e){
            takeSnapShot("003-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }

    @Test (priority=4, description = "004: Autenticación (contraseña vacía)")
    //004: Autenticación (contraseña vacía)
    public void verifyEmptyPasswordAuthentication() {

        try {

            typeCredentials(Environment.getProperties().username(),null);

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getPasswordRequiredMessage());
            
        }
        catch(Exception e){
            takeSnapShot("004-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }

    @Test (priority=5, description = "005: Autenticación (con credenciales vacías)")
    //005: Autenticación (con credenciales vacías)
    public void verifyEmptyCredentialsAuthentication() {

        try {

            typeCredentials(null,null);

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getUsernameRequiredMessage());
            
        }
        catch(Exception e){
            takeSnapShot("005-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }

    @Test (priority=6, description = "006: Autenticación (Redirección a la vista de autenticación)")
    //006: Autenticación (Redirección a la vista de autenticación)
    public void verifyRedirectionToAuthentication() {
        try {

            loginPage.load();

            driver.get(Environment.getProperties().url() + Environment.getProperties().home_page());            
            
            synchronized (driver)
            {
                driver.wait(1000);
            }                 

            assertThat(driver.getCurrentUrl()).isEqualTo(Environment.getProperties().url());

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getAccessNotAllowedMessage());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch(Exception e2){
            takeSnapShot("006-"+ System.currentTimeMillis() +".jpg");
           
            Fail.fail(e2.getMessage());
        }
    }

    @Test (priority=7, description = "007: Autenticación (problemas de performance)")
    //007: Autenticación (problemas de performance)
    public void verifyLoadingComponentAuthentication() {

        try {          

                typeCredentials(Environment.getProperties().username(),Environment.getProperties().password());
                assertThat(loginPage.getLoading().isDisplayed()).isTrue();

                /*synchronized (driver)
                    {
                        
                        driver.wait(1000);
                    
                    }  */

                assertThat(driver.getCurrentUrl()).isEqualTo(Environment.getProperties().url() + Environment.getProperties().home_page());

            //} catch (InterruptedException e) {
                               
            }
            catch(Exception e2){
                takeSnapShot("007-"+ System.currentTimeMillis() +".jpg");
               
                Fail.fail(e2.getMessage());
            }
    }

    @Test (priority=8, description = "008: Autenticación (nombre de usuario incorrecto)")
    //008: Autenticación (nombre de usuario incorrecto)
    public void verifyWrongUsernameAuthentication() {
        try {

            typeCredentials(Environment.getProperties().password(),Environment.getProperties().password());

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getNotFoundUserMessage());
    
            
        }
        catch(Exception e){
            takeSnapShot("008-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }

    @Test (priority=9, description = "009: Autenticación (contraseña incorrecta)" )
    //009: Autenticación (contraseña incorrecta)
    public void verifyWrongPasswordAuthentication() {

        try {

            typeCredentials(Environment.getProperties().username(),Environment.getProperties().username());

            assertThat(loginPage.getErrorMessage().getText()).isEqualTo(loginPage.getNotFoundUserMessage());
            
        }
        catch(Exception e){
            takeSnapShot("009-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }

        
    }    

    @Test (priority=10, description = "001: Autenticación (flujo básico)")
    //001: Autenticación (flujo básico)
    public void verifyBasicAuthentication() {

        try {

            typeCredentials(Environment.getProperties().username(),Environment.getProperties().password());

            assertThat(driver.getCurrentUrl()).isEqualTo(Environment.getProperties().url() + Environment.getProperties().home_page());
        }
        catch(Exception e){
            takeSnapShot("001-"+ System.currentTimeMillis() +".jpg");
            
            Fail.fail(e.getMessage());
        }       

    }

    @Test (priority=11, description = "011: Autenticación – Remover mensaje de error")
    //011: Autenticación – Remover mensaje de error
    public void verifyRemoveErrorMessageAuthentication() {

        try {

            typeCredentials(null,null);

            assertThat(loginPage.getErrorMessage().isDisplayed()).isTrue();

            assertThat(loginPage.getUsernameErrorInput().isDisplayed()).isTrue();

            assertThat(loginPage.getPasswordErrorInput().isDisplayed()).isTrue();

            assertThat(loginPage.getErrorMessageComponents().size()).isEqualTo(4);

            loginPage.clickCloseErrorMessage();

            assertThat(loginPage.getErrorMessageComponents().size()).isEqualTo(0);
        }
        catch(Exception e){
            takeSnapShot("011-"+ System.currentTimeMillis() +".jpg");
            Fail.fail(e.getMessage());
        }       

    }



    private void typeCredentials(String username, String password){
        
        loginPage.load();

        if(username != null){
            loginPage.typeUsername(username);
        }

        if(password != null){
            loginPage.typePassword(password);
        }

        loginPage.clickLoginButton();
    }

    
}
