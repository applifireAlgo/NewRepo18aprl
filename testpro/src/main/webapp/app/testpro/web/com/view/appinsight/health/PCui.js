Ext.define('Testpro.testpro.web.com.view.appinsight.health.PCui', {
     "xtype": "pCui",
     "items": [{
          "xtype": "tabpanel",
          "items": [{
               "xtype": "panel",
               "items": [{
                    "xtype": "form",
                    "items": [{
                         "xtype": "textfield",
                         "fieldLabel": "Name",
                         "margin": 5,
                         "bindable": "pnm",
                         "name": "pnm",
                         "itemId": "textfield_ext_10556"
                    }, {
                         "xtype": "numberfield",
                         "fieldLabel": "ParentAge",
                         "name": "age",
                         "margin": 5,
                         "bindable": "age",
                         "itemId": "numberfield_ext_10588"
                    }],
                    "border": true,
                    "autoScroll": true,
                    "margin": 5,
                    "itemId": "form_ext_10489",
                    "dockedItems": []
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "Tab-1",
               "tabId": 1,
               "itemId": "panel_ext_10371",
               "dockedItems": []
          }, {
               "xtype": "panel",
               "items": [{
                    "xtype": "form",
                    "items": [{
                         "xtype": "textfield",
                         "fieldLabel": "CName",
                         "margin": 5,
                         "bindable": "cnm",
                         "name": "cnm",
                         "itemId": "textfield_ext_10852"
                    }, {
                         "xtype": "numberfield",
                         "fieldLabel": "Age",
                         "name": "cage",
                         "margin": 5,
                         "bindable": "cage",
                         "itemId": "numberfield_ext_10893"
                    }, {
                         "xtype": "grids",
                         "name": "aa",
                         "title": "Grid",
                         "autoScroll": true,
                         "hiddenName": "Grid",
                         "margin": 5,
                         "collapseMode": "header",
                         "bindable": "child",
                         "border": true,
                         "editTools": false,
                         "features": [],
                         "plugins": [{
                              "ptype": "cellediting",
                              "clicksToEdit": 1
                         }],
                         "columns": [{
                              "xtype": "gridcolumn",
                              "header": "pid",
                              "hidden": true,
                              "dataIndex": "pid",
                              "flex": 1
                         }, {
                              "xtype": "gridcolumn",
                              "header": "pName",
                              "dataIndex": "pName",
                              "flex": 1
                         }, {
                              "xtype": "gridcolumn",
                              "header": "age",
                              "dataIndex": "age",
                              "flex": 1
                         }, {
                              "xtype": "gridcolumn",
                              "header": "primaryDisplay",
                              "hidden": true,
                              "dataIndex": "primaryDisplay",
                              "flex": 1
                         }],
                         "itemId": "gridpanel_ext_11153",
                         "isRelatedWith": "mjemkggai",
                         "store": {
                              "autoLoad": true,
                              "autoSync": true,
                              "model": "Testpro.testpro.shared.com.model.appinsight.health.ParentModel",
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Parent/findAll",
                                   "serviceId": "776BB5A2-F6BA-42E6-8A79-3CA1A98E4230",
                                   "serviceOperationId": "7A3E36E9-58F7-4799-9902-1419FEC85C67",
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
                         },
                         "tools": [{
                              "type": "refresh",
                              "tooltip": "Refresh Grid Data",
                              "handler": "onGridRefreshClick"
                         }]
                    }, {
                         "xtype": "button",
                         "name": "Save",
                         "text": "Save",
                         "margin": 5,
                         "itemId": "button_ext_11532",
                         "listeners": {
                              "click": "onSaveClick"
                         }
                    }],
                    "border": true,
                    "autoScroll": true,
                    "margin": 5,
                    "itemId": "form_ext_10772",
                    "dockedItems": []
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "Tab-2",
               "tabId": 2,
               "itemId": "panel_ext_10396",
               "dockedItems": []
          }],
          "autoScroll": true,
          "activeItem": 0,
          "activeTab": 0,
          "title": "Tab Layout",
          "margin": 5,
          "itemId": "tabpanel_ext_10352",
          "dockedItems": []
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_10291",
     "dockedItems": [],
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.ParentModel", "Testpro.testpro.web.com.controller.appinsight.health.PCuiController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.PCuiViewModel", "Testpro.testpro.shared.com.model.appinsight.health.PCuiModel", "Testpro.view.fw.component.Grids"],
     "extend": "Ext.form.Panel",
     "viewModel": "PCuiViewModel",
     "controller": "PCuiController"
});