Ext.define('Testpro.testpro.web.com.view.appinsight.health.TestOneMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TestOneMainController",
     "restURL": "/TestOne",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.TestOneModel", "Testpro.testpro.web.com.controller.appinsight.health.TestOneMainController", "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel", "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel", "Testpro.testpro.shared.com.viewmodel.appinsight.health.TestOneViewModel"],
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
               "displayName": "TestOne",
               "name": "TestOneTreeContainer",
               "itemId": "TestOneTreeContainer",
               "restURL": "/TestOne",
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
                    "itemId": "TestOneTree",
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
                    "displayName": "TestOne",
                    "title": "TestOne",
                    "name": "TestOne",
                    "itemId": "TestOneForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "tid",
                         "itemId": "tid",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "tid",
                         "margin": "5 5 5 5",
                         "fieldLabel": "tid<font color='red'> *<\/font>",
                         "fieldId": "A3E3DBB3-6964-4D35-9BFF-D89E9FFC81B3",
                         "hidden": true,
                         "value": "",
                         "bindable": "tid"
                    }, {
                         "name": "tnm",
                         "itemId": "tnm",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "tnm",
                         "margin": "5 5 5 5",
                         "fieldLabel": "tnm<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "DC002717-01C4-4CCE-B2ED-46CC3468ABD4",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "tnm",
                         "columnWidth": 0.5
                    }, {
                         "name": "tno",
                         "itemId": "tno",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "tno",
                         "margin": "5 5 5 5",
                         "fieldLabel": "tno<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "5D972F1B-3F47-4D56-BAD3-C9522280C03C",
                         "minValue": "-2147483648",
                         "maxValue": "2147483647",
                         "bindable": "tno",
                         "columnWidth": 0.5
                    }, {
                         "name": "gen",
                         "itemId": "gen",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Gender",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Gender<font color='red'> *<\/font>",
                         "fieldId": "F46E57FE-16C1-42C6-92E9-C3328A974687",
                         "restURL": "Gender",
                         "bindable": "gen",
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
                         "fieldId": "90D38802-8FCC-4F67-BDCA-2A07A64431D9",
                         "bindable": "versionId",
                         "hidden": true
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
                              "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel"
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
                         "customId": 421,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 421,
                              "customId": 258
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 421,
                              "customId": 259,
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
                              "parentId": 421,
                              "customId": 260,
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
                    "displayName": "TestOne",
                    "title": "Details Grid",
                    "name": "TestOneGrid",
                    "itemId": "TestOneGrid",
                    "restURL": "/TestOne",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "tid",
                         "dataIndex": "tid",
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
                         "header": "tnm",
                         "dataIndex": "tnm",
                         "flex": 1
                    }, {
                         "header": "tno",
                         "dataIndex": "tno",
                         "flex": 1
                    }, {
                         "header": "Gender",
                         "dataIndex": "gen",
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
               "displayName": "TestOne",
               "title": "TestOne",
               "name": "TestOne",
               "itemId": "TestOneForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "tid",
                    "itemId": "tid",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "tid",
                    "margin": "5 5 5 5",
                    "fieldLabel": "tid<font color='red'> *<\/font>",
                    "fieldId": "A3E3DBB3-6964-4D35-9BFF-D89E9FFC81B3",
                    "hidden": true,
                    "value": "",
                    "bindable": "tid"
               }, {
                    "name": "tnm",
                    "itemId": "tnm",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "tnm",
                    "margin": "5 5 5 5",
                    "fieldLabel": "tnm<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "DC002717-01C4-4CCE-B2ED-46CC3468ABD4",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "tnm",
                    "columnWidth": 0.5
               }, {
                    "name": "tno",
                    "itemId": "tno",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "tno",
                    "margin": "5 5 5 5",
                    "fieldLabel": "tno<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "5D972F1B-3F47-4D56-BAD3-C9522280C03C",
                    "minValue": "-2147483648",
                    "maxValue": "2147483647",
                    "bindable": "tno",
                    "columnWidth": 0.5
               }, {
                    "name": "gen",
                    "itemId": "gen",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Gender",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Gender<font color='red'> *<\/font>",
                    "fieldId": "F46E57FE-16C1-42C6-92E9-C3328A974687",
                    "restURL": "Gender",
                    "bindable": "gen",
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
                    "fieldId": "90D38802-8FCC-4F67-BDCA-2A07A64431D9",
                    "bindable": "versionId",
                    "hidden": true
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
                         "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel"
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
                    "customId": 421,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 421,
                         "customId": 258
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 421,
                         "customId": 259,
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
                         "parentId": 421,
                         "customId": 260,
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