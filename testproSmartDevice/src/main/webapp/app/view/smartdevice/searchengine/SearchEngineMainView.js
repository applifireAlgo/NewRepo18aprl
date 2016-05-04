Ext.define('Testpro.view.smartdevice.searchengine.SearchEngineMainView', {
	extend : 'Ext.tab.Panel',
	xtype : 'search-engine-main-view',
	requires : ['Testpro.view.smartdevice.searchengine.ChartDetailView','Testpro.view.smartdevice.searchengine.ChartListView','Ext.view.View','Testpro.view.smartdevice.searchengine.SearchEngineMainViewController'],//'Testpro.view.searchengine.search.NorthPanel', 'Testpro.view.searchengine.search.SearchResult'],
	controller : 'search-engine-main-view',
	title:'',
	tabPosition :'bottom',
	tabBar: {
        layout: { type:'hbox',pack: 'center' },
        defaults:{
        	height:40,
        	flex:1
        }
    },
    dockedItems:[],
	items:[{
		xtype:'panel',
		itemId:'chartPanel',
		layout:'card',
		title:"Chart",
		iconAlign:'top',
		icon:'images/rpticon/chart.png',
		items:[
		       {
		    	   xtype:'chart-list-view'
		       },
		       {
		    	   xtype:'chart-detail-view'
		       }
		]
	},
	{
		xtype:'panel',
		title:'Documents',
		iconAlign:'top',
		icon:'images/rpticon/chart.png'
	}, ,{
		xtype:'panel',
		title:'Map',
		iconAlign:'top',
		icon:'images/rpticon/maps.png'
	}         
	],
	
	listeners:{
		scope:'controller',
		afterrender:'onAfterRender'
	}
});
