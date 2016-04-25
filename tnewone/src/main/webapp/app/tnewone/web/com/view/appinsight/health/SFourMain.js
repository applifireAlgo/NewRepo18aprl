Ext.define('Tnewone.tnewone.web.com.view.appinsight.health.SFourMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "SFourMainController",
     "restURL": "/SFour",
     "defaults": {
          "split": true
     },
     "requires": ["Tnewone.tnewone.shared.com.model.appinsight.health.SFourModel", "Tnewone.tnewone.web.com.controller.appinsight.health.SFourMainController", "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel", "Tnewone.tnewone.shared.com.model.appinsight.health.SoneModel", "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.LoginModel", "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.UserModel", "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.TitleModel", "Tnewone.tnewone.shared.com.viewmodel.appinsight.health.SFourViewModel"],
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
               "displayName": "SFour",
               "name": "SFourTreeContainer",
               "itemId": "SFourTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "SFourTree",
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
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
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
                    "xtype": "form",
                    "displayName": "SFour",
                    "name": "SFour",
                    "itemId": "SFourForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "sFourid",
                                   "itemId": "sFourid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "SFour",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "SFour<font color='red'> *<\/font>",
                                   "fieldId": "069DD397-1D2B-4F85-9723-F674B4D1BD73",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "sFourid"
                              }, {
                                   "name": "sds",
                                   "itemId": "sds",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "sds",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "sds<font color='red'> *<\/font>",
                                   "fieldId": "B096C7D7-4A18-4B39-8D82-E57901F2D61B",
                                   "restURL": "CoreContacts",
                                   "bindable": "sds",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "sffdf",
                                   "itemId": "sffdf",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "dfdf",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Tnewone.tnewone.shared.com.model.appinsight.health.SoneModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "dfdf<font color='red'> *<\/font>",
                                   "fieldId": "E22E2377-D2C5-4FB8-A5CD-CD195F51D18C",
                                   "restURL": "Sone",
                                   "bindable": "sffdf",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "sdfs",
                                   "itemId": "sdfs",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "fsf",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.LoginModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "fsf<font color='red'> *<\/font>",
                                   "fieldId": "0C9B8394-8D4B-4E1F-AAD6-512793D2917A",
                                   "restURL": "Login",
                                   "bindable": "sdfs",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "fs",
                                   "itemId": "fs",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "sfs",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.UserModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "sfs<font color='red'> *<\/font>",
                                   "fieldId": "429B510E-3483-453B-95CD-00FBAD2C146B",
                                   "restURL": "User",
                                   "bindable": "fs",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "dfd",
                                   "itemId": "dfd",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "dfd",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.TitleModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "dfd<font color='red'> *<\/font>",
                                   "fieldId": "E665C4A5-DD22-4F68-B6F3-1275C838DD7B",
                                   "restURL": "Title",
                                   "bindable": "dfd",
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
                                   "fieldId": "FD5E7E66-BF1F-407D-86C8-2A9C3E1AC835",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "margin": 0,
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {
                              "margin": "0 5 0 5"
                         }
                    }],
                    "listeners": {},
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "SFour",
                    "title": "Details Grid",
                    "name": "SFourGrid",
                    "itemId": "SFourGrid",
                    "store": [],
                    "bodyPadding": 10,
                    "requires": [],
                    "columns": [{
                         "header": "SFour",
                         "dataIndex": "sFourid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "sds",
                         "dataIndex": "sds",
                         "flex": 1
                    }, {
                         "header": "dfdf",
                         "dataIndex": "sffdf",
                         "flex": 1
                    }, {
                         "header": "fsf",
                         "dataIndex": "sdfs",
                         "flex": 1
                    }, {
                         "header": "sfs",
                         "dataIndex": "fs",
                         "flex": 1
                    }, {
                         "header": "dfd",
                         "dataIndex": "dfd",
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
               "xtype": "form",
               "displayName": "SFour",
               "name": "SFour",
               "itemId": "SFourForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "sFourid",
                              "itemId": "sFourid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "SFour",
                              "margin": "5 5 5 5",
                              "fieldLabel": "SFour<font color='red'> *<\/font>",
                              "fieldId": "069DD397-1D2B-4F85-9723-F674B4D1BD73",
                              "hidden": true,
                              "value": "",
                              "bindable": "sFourid"
                         }, {
                              "name": "sds",
                              "itemId": "sds",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "sds",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.CoreContactsModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "sds<font color='red'> *<\/font>",
                              "fieldId": "B096C7D7-4A18-4B39-8D82-E57901F2D61B",
                              "restURL": "CoreContacts",
                              "bindable": "sds",
                              "columnWidth": 0.5
                         }, {
                              "name": "sffdf",
                              "itemId": "sffdf",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "dfdf",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Tnewone.tnewone.shared.com.model.appinsight.health.SoneModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "dfdf<font color='red'> *<\/font>",
                              "fieldId": "E22E2377-D2C5-4FB8-A5CD-CD195F51D18C",
                              "restURL": "Sone",
                              "bindable": "sffdf",
                              "columnWidth": 0.5
                         }, {
                              "name": "sdfs",
                              "itemId": "sdfs",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "fsf",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.LoginModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "fsf<font color='red'> *<\/font>",
                              "fieldId": "0C9B8394-8D4B-4E1F-AAD6-512793D2917A",
                              "restURL": "Login",
                              "bindable": "sdfs",
                              "columnWidth": 0.5
                         }, {
                              "name": "fs",
                              "itemId": "fs",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "sfs",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Tnewone.tnewone.shared.com.model.aaaboundedcontext.authentication.UserModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "sfs<font color='red'> *<\/font>",
                              "fieldId": "429B510E-3483-453B-95CD-00FBAD2C146B",
                              "restURL": "User",
                              "bindable": "fs",
                              "columnWidth": 0.5
                         }, {
                              "name": "dfd",
                              "itemId": "dfd",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "dfd",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.TitleModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "dfd<font color='red'> *<\/font>",
                              "fieldId": "E665C4A5-DD22-4F68-B6F3-1275C838DD7B",
                              "restURL": "Title",
                              "bindable": "dfd",
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
                              "fieldId": "FD5E7E66-BF1F-407D-86C8-2A9C3E1AC835",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "margin": 0,
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {
                         "margin": "0 5 0 5"
                    }
               }],
               "listeners": {},
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});