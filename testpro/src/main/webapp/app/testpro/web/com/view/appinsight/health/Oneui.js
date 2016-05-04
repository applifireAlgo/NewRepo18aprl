Ext.define('Testpro.testpro.web.com.view.appinsight.health.Oneui', {
     "xtype": "oneui",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "Name",
          "margin": 5,
          "bindable": "tnm",
          "name": "tnm",
          "itemId": "textfield_ext_9966"
     }, {
          "xtype": "numberfield",
          "fieldLabel": "Number",
          "name": "tno",
          "margin": 5,
          "bindable": "tno",
          "itemId": "numberfield_ext_9977"
     }, {
          "xtype": "button",
          "name": "Save",
          "text": "Save",
          "margin": 5,
          "itemId": "button_ext_10039",
          "listeners": {
               "click": "onSaveClick"
          }
     }, {
          "xtype": "button",
          "text": "Reset",
          "isResetButton": true,
          "margin": 5,
          "name": "Reset",
          "itemId": "button_ext_10053",
          "listeners": {
               "click": "onResetClick"
          }
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_8665",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Testpro.testpro.web.com.controller.appinsight.health.OneuiController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.OneuiViewModel", "Testpro.testpro.shared.com.model.appinsight.health.OneuiModel"],
     "viewModel": "OneuiViewModel",
     "controller": "OneuiController"
});