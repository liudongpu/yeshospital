var census = {
		
	temp:{
		score:0
	},
		
	sum_detail:function(o)
	{
		
		census.sum_all();
	},

	sum_all : function() {
		
		var aSum=0;

		for ( var i in census_detail_objs) {
			var oDetail = census_detail_objs[i];
			
			var aVal=[];
			$('input[name="census_d_'+oDetail["d_code"]+'"]:checked').each(
					
				function(n,el){
					
					aVal.push($(el).val());
					
					aSum=aSum+parseInt( $(el).attr("option-score"));
					
				}
			);
			
			
			
			
			
			//console.log(oDetail["d_code"]+':'+aVal.join(','));
			
		}
		
		
		$('#census_sum_score').html(aSum);
		
		
		var sText=[];
		
		for ( var i in census_standard_objs) {
			var oStandard = census_standard_objs[i];
			
			if(aSum>parseInt(oStandard["d_min"])&&aSum<=parseInt(oStandard["d_max"]))
				{
					sText.push('<span class="census_standard_item" >'+oStandard["d_name"]+'</span>&nbsp;&nbsp;'+oStandard["d_desc"]);
				}
			
			
		}
		
		
		
		$('#census_sum_standard').html(sText.join('<br/>'));
		
		

	}

};
