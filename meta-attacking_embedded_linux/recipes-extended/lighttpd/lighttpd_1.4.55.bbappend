do_install:append() {
        rm -f ${D}/etc/lighttpd/lighttpd.conf
	rm -f ${D}/www/pages/index.html
        chmod 777 ${D}/www/pages        
}

