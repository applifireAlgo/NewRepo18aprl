Ext.define('Tnewone.tnewone.web.com.view.appinsight.health.Cpui', {
     "xtype": "cpui",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "aa",
          "name": "aa",
          "itemId": "textfield_ext_5528"
     }, {
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
          "itemId": "combo_ext_5538",
          "store": {
               "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.location.CountryModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "countryName",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/Country/findAll",
                    "serviceId": "9EB924A0-E13E-4D9C-AD07-DD9AD6509A5B",
                    "serviceOperationId": "40EC45A3-953C-4F6B-9EC8-85DEDA36B3D9",
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
     "itemId": "form_ext_5516",
     "dockedItems": [],
     "requires": ["Tnewone.tnewone.shared.com.model.organizationboundedcontext.location.CountryModel", "Tnewone.tnewone.web.com.controller.appinsight.health.CpuiController", "Tnewone.tnewone.shared.com.viewmodel.appinsight.health.CpuiViewModel", "Tnewone.tnewone.shared.com.model.appinsight.health.CpuiModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "CpuiViewModel",
     "controller": "CpuiController"
});