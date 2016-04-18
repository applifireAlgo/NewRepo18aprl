Ext.define('Testtwo.testtwo.web.com.view.appinsight.health.SSSSS', {
     "xtype": "sSSSS",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t1",
          "name": "t1",
          "itemId": "textfield_ext_8768"
     }, {
          "xtype": "combo",
          "name": "c1",
          "margin": 5,
          "bindable": "c1",
          "typeAhead": true,
          "queryMode": "local",
          "minChars": 1,
          "fieldLabel": "ComboBox",
          "displayField": "bnm",
          "valueField": "bbid",
          "itemId": "combo_ext_8779",
          "store": {
               "model": "Testtwo.testtwo.shared.com.model.appinsight.health.BBModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "bnm",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/BB/findAll",
                    "serviceId": "86ED758E-BCD6-4F22-8865-8B81891C6ED0",
                    "serviceOperationId": "7D23076A-9BD3-49CC-BCFF-FAE1582723A9",
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
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t2",
          "name": "t2",
          "itemId": "textfield_ext_8791"
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t3",
          "name": "t3",
          "itemId": "textfield_ext_8806"
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t4",
          "name": "t4",
          "itemId": "textfield_ext_8823"
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t5",
          "name": "t5",
          "itemId": "textfield_ext_8841"
     }, {
          "xtype": "button",
          "name": "b1",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8861",
          "listeners": {
               "click": "onB1Click"
          }
     }, {
          "xtype": "button",
          "name": "b2",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8883",
          "listeners": {
               "click": "onB2Click"
          }
     }, {
          "xtype": "button",
          "name": "b3",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8908",
          "listeners": {
               "click": "onB3Click"
          }
     }, {
          "xtype": "button",
          "name": "b4",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8934",
          "listeners": {
               "click": "onB4Click"
          }
     }, {
          "xtype": "button",
          "name": "b5",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8962",
          "listeners": {
               "click": "onB5Click"
          }
     }, {
          "xtype": "button",
          "name": "b6",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_8992",
          "listeners": {
               "click": "onB6Click"
          }
     }, {
          "xtype": "button",
          "name": "b7",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_9024",
          "listeners": {
               "click": "onB7Click"
          }
     }, {
          "xtype": "button",
          "name": "b8",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_9058",
          "listeners": {
               "click": "onB8Click"
          }
     }, {
          "xtype": "button",
          "name": "b9",
          "text": "Button",
          "margin": 5,
          "itemId": "button_ext_9094",
          "listeners": {
               "click": "onB9Click"
          }
     }, {
          "xtype": "combo",
          "name": "c2",
          "margin": 5,
          "bindable": "c2",
          "typeAhead": true,
          "queryMode": "local",
          "minChars": 1,
          "fieldLabel": "ComboBox",
          "displayField": "addressType",
          "valueField": "addressTypeId",
          "itemId": "combo_ext_9133",
          "store": {
               "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.AddressTypeModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "addressType",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/AddressType/findAll",
                    "serviceId": "22016FDD-EBD4-4871-B852-7E34E165DC6F",
                    "serviceOperationId": "AEF0E0E8-7B80-4834-B6E1-C6FDE5D60416",
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
          },
          "listeners": {
               "change": "onC2Change"
          }
     }, {
          "xtype": "combo",
          "name": "c3",
          "margin": 5,
          "bindable": "c3",
          "typeAhead": true,
          "queryMode": "local",
          "minChars": 1,
          "fieldLabel": "ComboBox",
          "displayField": "menuTreeId",
          "valueField": "menuId",
          "itemId": "combo_ext_9173",
          "store": {
               "model": "Testtwo.testtwo.shared.com.model.aaaboundedcontext.authorization.AppMenusModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "menuTreeId",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/AppMenus/findAll",
                    "serviceId": "2908887B-8D44-4E68-AE4A-4F5EC5CA8DE4",
                    "serviceOperationId": "122DBE00-2FCC-4F62-8484-7CA2533B0D67",
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
          },
          "listeners": {
               "change": "onC3Change"
          }
     }, {
          "xtype": "combo",
          "name": "c4",
          "margin": 5,
          "bindable": "c4",
          "typeAhead": true,
          "queryMode": "local",
          "minChars": 1,
          "fieldLabel": "ComboBox",
          "displayField": "contactType",
          "valueField": "contactTypeId",
          "itemId": "combo_ext_9215",
          "store": {
               "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.contacts.ContactTypeModel",
               "autoLoad": true,
               "autoSync": true,
               "sorters": [{
                    "property": "contactType",
                    "direction": "ASC"
               }],
               "proxy": {
                    "type": "ajax",
                    "url": "secure/ContactType/findAll",
                    "serviceId": "0FEC92F0-DEFE-4B75-8E77-FF1243637590",
                    "serviceOperationId": "715BD3AB-BD4B-436C-AE6E-75C1B48D8388",
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
          },
          "listeners": {
               "change": "onC4Change"
          }
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_8756",
     "dockedItems": [],
     "requires": ["Testtwo.testtwo.shared.com.model.appinsight.health.BBModel", "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.AddressTypeModel", "Testtwo.testtwo.shared.com.model.aaaboundedcontext.authorization.AppMenusModel", "Testtwo.testtwo.shared.com.model.organizationboundedcontext.contacts.ContactTypeModel", "Testtwo.testtwo.web.com.controller.appinsight.health.SSSSSController", "Testtwo.testtwo.shared.com.viewmodel.appinsight.health.SSSSSViewModel", "Testtwo.testtwo.shared.com.model.appinsight.health.SSSSSModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "SSSSSViewModel",
     "controller": "SSSSSController"
});