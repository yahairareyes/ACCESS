function hideView(view, opt1, opt2){
	
document.getElementById(view).style.display = 'none';
document.getElementById(opt1).checked = true;	
document.getElementById(opt2).checked = false;
}

function showView(view, opt1, opt2){
	
document.getElementById(view).style.display = 'block';
document.getElementById(opt1).checked = true;
document.getElementById(opt2).checked = false;	
}

function hideLinker(linker){
document.getElementById(linker).style.display = 'none';	
}

function showLinker(linker){
document.getElementById(linker).style.display = 'block';	
	
}


