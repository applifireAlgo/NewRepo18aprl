Ext.define('Testpro.testpro.web.com.view.appinsight.health.Test2ui', {
     "xtype": "test2ui",
     "items": [{
          "xtype": "combo",
          "name": "countryId",
          "margin": 5,
          "bindable": "countryId",
          "typeAhead": true,
          "queryMode": "local",
          "minChars": 1,
          "fieldLabel": "C",
          "displayField": "countryName",
          "valueField": "countryId",
          "itemId": "combo_ext_28547",
          "store": {
               "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.CountryModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "countryName",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/Country/findAll",
                    "serviceId": "0CC6D759-6B95-4032-8FAF-282BCBE0478E",
                    "serviceOperationId": "BE29B8C9-AB38-4E2D-A7A8-7454DE694030",
                    "serviceType": 1,
                    "actionMethods": {
                         "read": "GET"
                    },
                    "headers": {
                         "Content-Type": "application/json"
                    },
                    "extraParams": {},
                    "reader": {
                         "type": "json",
                         "rootProperty": "response.data"
                    }
               }
          }
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_28539",
     "dockedItems": [],
     "requires": ["Testpro.testpro.shared.com.model.organization.locationmanagement.CountryModel", "Testpro.testpro.web.com.controller.appinsight.health.Test2uiController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.Test2uiViewModel", "Testpro.testpro.shared.com.model.appinsight.health.Test2uiModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "Test2uiViewModel",
     "controller": "Test2uiController"
});