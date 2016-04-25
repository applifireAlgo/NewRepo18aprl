Ext.define('Tnewone.tnewone.web.com.view.appinsight.health.Listui', {
     "xtype": "listui",
     "items": [],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_5723",
     "requires": ["Tnewone.tnewone.web.com.view.appinsight.health.Cpui", "Tnewone.tnewone.web.com.controller.appinsight.health.ListuiController", "Tnewone.tnewone.shared.com.viewmodel.appinsight.health.ListuiViewModel", "Tnewone.tnewone.shared.com.model.appinsight.health.ListuiModel"],
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt5723Afterrender",
          "scope": "controller"
     },
     "viewModel": "ListuiViewModel",
     "controller": "ListuiController"
});