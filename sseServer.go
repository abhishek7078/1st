package main

import (
	"fmt"
	"time"
	"net/http"
)

var id int
id:=1
func main()
{
http.Handle("ssehandler",handlereq)
log.Fatal(http.ListenandServe(":8000",nil)
}
func handlereq(w http.ResponseWriter, r *http.Request){
	w.Header().Set("Content-Type","text/event-stream")
	w.Header().Set("Cache-Control","no-cache")
	w.Header().Set("Connection","keep-alive")
	for {
		w.Write(id);
		fmt.Println(id)
		id++
	}
}

