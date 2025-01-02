package org.karensystem.core.exceptions.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "msg")
@Component
@Data
public class MessagesConfig {
    //Generic msg
    private String vchIsPerm="SRV_ERR_0001";

    //Database Errors
    private String constraintViolationException="SRV_ERR_0002" ;
    private String duplicationRecord="SRV_ERR_0003" ;


    //User Error
    private String emptyPassword="SRV_ERR_0004";

    //Sto Err
    private String partQtyIsNotEnough="SRV_ERR_0005%x";
    private String stoYearIsClosed="SRV_ERR_0006";
    private String stoPricedByBaseVch="SRV_ERR_0007";

    //Tre Err
    private String tre="SRV_ERR_0005%x";


    //AccError
    private String oneOfTheDocsHasAcc="SRV_ERR_0008";
    private String docMustBePricedFirst="SRV_ERR_0009";
}
