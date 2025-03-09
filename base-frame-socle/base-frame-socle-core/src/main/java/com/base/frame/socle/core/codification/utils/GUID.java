
package com.base.frame.socle.core.codification.utils;

import java.util.UUID;

/**
 *
 * @author Adnaane
 * @version 1.0.0
 * @since 28/02/2018
 *
 */
public class GUID {

    public GUID() {
        //Do nothing
    }
    

    public static final String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
