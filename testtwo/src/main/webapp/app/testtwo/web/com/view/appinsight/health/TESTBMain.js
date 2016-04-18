Ext.define('Testtwo.testtwo.web.com.view.appinsight.health.TESTBMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TESTBMainController",
     "restURL": "/TESTB",
     "defaults": {
          "split": true
     },
     "requires": ["Testtwo.testtwo.shared.com.model.appinsight.health.TESTBModel", "Testtwo.testtwo.web.com.controller.appinsight.health.TESTBMainController", "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.CountryModel", "Testtwo.testtwo.shared.com.model.appinsight.health.TestOneModel", "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.AddressModel", "Testtwo.testtwo.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel", "Testtwo.testtwo.shared.com.viewmodel.appinsight.health.TESTBViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "TESTB",
               "name": "TESTBTreeContainer",
               "itemId": "TESTBTreeContainer",
               "restURL": "/TESTB",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "TESTBTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "text": "Filter",
                              "name": "filterButton",
                              "handler": "onFilterClick"
                         }]
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "TESTB",
                    "title": "TESTB",
                    "name": "TESTB",
                    "itemId": "TESTBForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "aaaaa",
                         "itemId": "aaaaa",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "aaaaa",
                         "margin": "5 5 5 5",
                         "fieldLabel": "aaaaa<font color='red'> *<\/font>",
                         "fieldId": "047F220A-8F1F-4AE8-AF78-38A403D7A1DB",
                         "hidden": true,
                         "value": "",
                         "bindable": "aaaaa"
                    }, {
                         "name": "bc",
                         "itemId": "bc",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Country",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.CountryModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Country<font color='red'> *<\/font>",
                         "fieldId": "3A4F98B3-7FCC-4A5E-8E87-4A9792A78642",
                         "restURL": "Country",
                         "bindable": "bc",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "675CFF50-B86D-46D2-89D9-7BC8430EFF05",
                         "bindable": "versionId",
                         "hidden": true
                    }, {
                         "xtype": "combo",
                         "name": "TestOne",
                         "displayField": "primaryDisplay",
                         "valueField": "primaryKey",
                         "margin": 5,
                         "bindable": "testOne.rrrr",
                         "typeAhead": true,
                         "columnWidth": 0.5,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "TestOne<font color='red'> *<\/font>",
                         "title": "TestOne",
                         "itemId": "testOne",
                         "store": {
                              "data": [],
                              "model": "Testtwo.testtwo.shared.com.model.appinsight.health.TestOneModel"
                         }
                    }, {
                         "xtype": "combo",
                         "name": "Address",
                         "displayField": "primaryDisplay",
                         "valueField": "primaryKey",
                         "margin": 5,
                         "bindable": "address.addressId",
                         "typeAhead": true,
                         "columnWidth": 0.5,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Address<font color='red'> *<\/font>",
                         "title": "Address",
                         "itemId": "address",
                         "store": {
                              "data": [],
                              "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.AddressModel"
                         }
                    }, {
                         "xtype": "combo",
                         "name": "CoreContacts",
                         "displayField": "primaryDisplay",
                         "valueField": "primaryKey",
                         "margin": 5,
                         "bindable": "coreContacts.contactId",
                         "typeAhead": true,
                         "columnWidth": 0.5,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Core Contacts<font color='red'> *<\/font>",
                         "title": "Core Contacts",
                         "itemId": "coreContacts",
                         "store": {
                              "data": [],
                              "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel"
                         }
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 607,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 607,
                              "customId": 922
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 607,
                              "customId": 923,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 607,
                              "customId": 924,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {}
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "TESTB",
                    "title": "Details Grid",
                    "name": "TESTBGrid",
                    "itemId": "TESTBGrid",
                    "restURL": "/TESTB",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "aaaaa",
                         "dataIndex": "aaaaa",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Country",
                         "dataIndex": "bc",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "width": 30,
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "TESTB",
               "title": "TESTB",
               "name": "TESTB",
               "itemId": "TESTBForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "aaaaa",
                    "itemId": "aaaaa",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "aaaaa",
                    "margin": "5 5 5 5",
                    "fieldLabel": "aaaaa<font color='red'> *<\/font>",
                    "fieldId": "047F220A-8F1F-4AE8-AF78-38A403D7A1DB",
                    "hidden": true,
                    "value": "",
                    "bindable": "aaaaa"
               }, {
                    "name": "bc",
                    "itemId": "bc",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Country",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.CountryModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Country<font color='red'> *<\/font>",
                    "fieldId": "3A4F98B3-7FCC-4A5E-8E87-4A9792A78642",
                    "restURL": "Country",
                    "bindable": "bc",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "675CFF50-B86D-46D2-89D9-7BC8430EFF05",
                    "bindable": "versionId",
                    "hidden": true
               }, {
                    "xtype": "combo",
                    "name": "TestOne",
                    "displayField": "primaryDisplay",
                    "valueField": "primaryKey",
                    "margin": 5,
                    "bindable": "testOne.rrrr",
                    "typeAhead": true,
                    "columnWidth": 0.5,
                    "queryMode": "local",
                    "minChars": 1,
                    "fieldLabel": "TestOne<font color='red'> *<\/font>",
                    "title": "TestOne",
                    "itemId": "testOne",
                    "store": {
                         "data": [],
                         "model": "Testtwo.testtwo.shared.com.model.appinsight.health.TestOneModel"
                    }
               }, {
                    "xtype": "combo",
                    "name": "Address",
                    "displayField": "primaryDisplay",
                    "valueField": "primaryKey",
                    "margin": 5,
                    "bindable": "address.addressId",
                    "typeAhead": true,
                    "columnWidth": 0.5,
                    "queryMode": "local",
                    "minChars": 1,
                    "fieldLabel": "Address<font color='red'> *<\/font>",
                    "title": "Address",
                    "itemId": "address",
                    "store": {
                         "data": [],
                         "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.location.AddressModel"
                    }
               }, {
                    "xtype": "combo",
                    "name": "CoreContacts",
                    "displayField": "primaryDisplay",
                    "valueField": "primaryKey",
                    "margin": 5,
                    "bindable": "coreContacts.contactId",
                    "typeAhead": true,
                    "columnWidth": 0.5,
                    "queryMode": "local",
                    "minChars": 1,
                    "fieldLabel": "Core Contacts<font color='red'> *<\/font>",
                    "title": "Core Contacts",
                    "itemId": "coreContacts",
                    "store": {
                         "data": [],
                         "model": "Testtwo.testtwo.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel"
                    }
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 607,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 607,
                         "customId": 922
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 607,
                         "customId": 923,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 607,
                         "customId": 924,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {}
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});