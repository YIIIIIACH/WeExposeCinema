<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>電影廳院院座位</title>
    <link rel="icon" type="image/x-icon" href="image/weExposeFavicon.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin: 0px 0%"> 
	<div style="margin: 7% 20%">
    <h4 align="center">請選擇您要預定的位置</h4>
    <div id="theater" class="theater"></div>
    <div>
    	<form action="/WeExpose/Booking" method="post">
    		<input type="text" name="showingId" value="${showingId }" style="display:none">
    		<input id="seatsStr" type="text" name="seatsStr" style="display:none" value="[]">
    		<button style="margin: 0px 80% 0px 15%" type="submit" id="bookBtn" class="btn btn-primary">訂票</button>	
    	</form>
    </div>
    </div>
    </div>
</body>
<script src="js/selectSeats.js"></script>
<script>
	let selectCnt = 0;
	let seatLayout = { ${seatsString} };
	let bookedSeats = [ ${bookedSeats}];
	let selectedSeats = [];
	document.getElementById('bookBtn').addEventListener('click',()=>{
		if( selectCnt<=0){
			event.preventDefault();
			return;
		}
		let str='';
		// refresh seatsStr from selectedSeats[];
		for( let i=0; i<selectedSeats.length ; i++){
			str+= selectedSeats[i][0]+ '-' + selectedSeats[i][1] ;
			if( i+1 < selectedSeats.length){
				str += ',';
			}
		}
		document.getElementById('seatsStr').setAttribute("value",str);
	});
    let modal = document.getElementById('theater');
    var modal_page={
    'modal': modal,
    'modal_content': [],// modal_content consits of string of html
    'threater': {},
    'toggle':function(){
        console.log( "toggle");
        if(modal.style.display=="none"){
            modal.style.display='';
        }
        else{
            modal.style.display= "none";
        }
    },
    'init':function(layout){
        this.threater = new threater(seatLayout);
    },
    'refreshUI':function(){
        modal_page.modal.innerHTML="";
        let cnt =0;
        for( let tmp of this.threater.rows){
            this.addSeatRow( tmp , cnt);
            cnt++;
        }
    },
    'addSeatRow':function( SeatsRow ,lv){
        let tmp = `<div class="seatRow" >`;
        for(let i=0; i< SeatsRow.row_Length; i++){
            let addedSeat = SeatsRow.seatOf(i);
            
            if( addedSeat.status =='booked'){
                tmp+= `<div class="seat booked"  ></div>`;
            }
            else if(addedSeat.status=='unbooked'){
                tmp+= `<div class="seat unbooked" ></div>`;
            }
            else if( addedSeat.status=='selected'){
            	tmp+= `<div class="seat" style="background-color: rgb(237, 212, 0);border: 3px solid rgb(230, 240, 194)"></div>`;
            }
        }
        tmp+=`</div>`;
        modal_page.modal.innerHTML+= tmp;
    },
    'setBooked':function( bookedSeats){
        for( let i=0; i< bookedSeats.length; i++){
        	
            let seatRow =this.threater.rows
            seatRow[ bookedSeats[i][0]-1].seatOf( bookedSeats[i][1]-1).status= "booked";
        }
    },
    'setSeatEventListener':function(){
        let rowIt = this.modal.firstChild;
        for( let row=1; row<= this.threater.rows.length; row++){
            let colIt = rowIt.firstChild;
            for( let col=1; col<= this.threater.rows[row-1].row_Length; col++){
                colIt.addEventListener('click' ,(event)=>{
                    let st= this.threater.rows[row-1].seatOf( col-1)
                    if(st.status== 'booked'){
                    	// if seat was booked by others do nothing
                    	;
                    }else if( st.status == 'unbooked'){
                    	st.status = 'selected';
                    	// push row col to selectedSeats;
                    	selectCnt++;
                    	selectedSeats.push([row, col]);
                    }
                    else if ( st.status== 'selected'){
                    	st.status = 'unbooked';
                    	selectCnt--;
                    	// find it in selectedSeats; and splic( i,1);
                    	for( let i=0; i< selectedSeats.length; i++){
                    		if( selectedSeats[i][0]==row && selectedSeats[i][1]==col){
                    			selectedSeats.splice( i, 1);
                    			break;
                    		}
                    	}
                    }
                    modal_page.refreshUI();
                    modal_page.setSeatEventListener();
                });
                colIt= colIt.nextSibling;
            }
            rowIt = rowIt.nextSibling;
        }
    }
};
modal_page.init();
modal_page.setBooked(bookedSeats);
modal_page.refreshUI();
modal_page.setSeatEventListener();

</script>
</html>