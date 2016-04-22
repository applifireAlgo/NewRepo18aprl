Ext.define('Tnewone.tnewone.web.com.view.appinsight.health.BBui', {
     "xtype": "bBui",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t1",
          "name": "t1",
          "itemId": "textfield_ext_4671"
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t2",
          "name": "t2",
          "itemId": "textfield_ext_4681"
     }, {
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "t3",
          "name": "t3",
          "itemId": "textfield_ext_4694"
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_4661",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Tnewone.tnewone.web.com.controller.appinsight.health.BBuiController", "Tnewone.tnewone.shared.com.viewmodel.appinsight.health.BBuiViewModel", "Tnewone.tnewone.shared.com.model.appinsight.health.BBuiModel"],
     "viewModel": "BBuiViewModel",
     "controller": "BBuiController"
});