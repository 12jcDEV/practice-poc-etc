/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager.impl;

import com.test.webcrawler.manager.URLManager;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("uRLManager")
public class URLManagerImpl implements URLManager {

    private String[] schemes = {"http", "https"};
    private UrlValidator urlValidator;
    
    public URLManagerImpl() {
        urlValidator = new UrlValidator(schemes);
    }
    
    @Override
    public boolean validateURL(String url) {
       return urlValidator.isValid(url);
    }
    
    
    
}
