DESCRIPTION = "Tiny File Manager "
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://config.php \
	   file://info.php \
	   file://tinyfilemanager.php \
	   file://lighttpd.conf \
	   file://php.ini \
	   file://index.html \
	   file://SW.jpg \
	   file://server.pem"
	   


S = "${WORKDIR}"

do_install() {
	mkdir   -p ${D}/www/pages/
	mkdir   -p ${D}/etc/php/apache2-php8/
	mkdir 	-p ${D}/etc/lighttpd/
	mkdir   -p ${D}/etc/lighttpd/ssl/domain.com/
	install -m 0755 server.pem ${D}/etc/lighttpd/ssl/domain.com/
        install -m 0755 config.php ${D}/www/pages/
        install -m 0755 info.php ${D}/www/pages/
        install -m 0755 tinyfilemanager.php ${D}/www/pages/
        install -m 0755 lighttpd.conf ${D}/etc/lighttpd/
        install -m 0755 php.ini ${D}/etc/php/apache2-php8/   
        install -m 0755 index.html ${D}/www/pages 
        install -m 0755 SW.jpg ${D}/www/pages
}

FILES:${PN} = "/www/pages/config.php \
	       /www/pages/info.php \
	       /www/pages/index.html \
	       /www/pages/tinyfilemanager.php \
	       /etc/lighttpd/lighttpd.conf \
	       /etc/php/apache2-php8/php.ini \
	       /etc/lighttpd/ssl/domain.com/server.pem \  
	       /www/pages/SW.jpg  \
	      "
