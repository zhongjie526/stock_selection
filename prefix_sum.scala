


def prefix_sum(a:Array[Int])={
	val p=new Array[Int](a.size+1)
	for(i<-1 to a.size){
		p(i) = p(i-1)+a(i-1)
	}
	p
}


def segment_sum(a:Array[Int],start:Int,end:Int)={
	val p = prefix_sum(a)
	p(end+1)-p(start)
}


def mushroom(a:Array[Int],k:Int,m:Int)={

	var max_mashroom=0

	for(left_steps<- 0 to Math.min(m,k)){
		val left_boundary = k-left_steps
		val right_boundary = Math.min(Math.max(k,left_boundary+(m - left_steps)),a.size-1)

		if(max_mashroom<segment_sum(a,left_boundary,right_boundary)){
			max_mashroom = segment_sum(a,left_boundary,right_boundary)
		}
	}

	for(right_steps<-0 to Math.min(m,a.size-1-k)){
		val right_boundary = k+right_steps
		val left_boundary = Math.max(Math.min(k,right_boundary-right_steps),0)
		if(max_mashroom<segment_sum(a,left_boundary,right_boundary)){
			max_mashroom = segment_sum(a,left_boundary,right_boundary)
		}
	}

	max_mashroom

}