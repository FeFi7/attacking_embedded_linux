DESCRIPTION = "openssl CVE-2022-0778"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://my_bad_sqrt.c \
          "
DEPENDS = "openssl"
          
S = "${WORKDIR}"

do_compile() {
	${CC} -lcrypto my_bad_sqrt.c ${LDFLAGS} -o my_bad_sqrt
}


do_install() {
	mkdir   -p ${D}/home/felix/opensslhack
	install -m 0755 my_bad_sqrt ${D}/home/felix/opensslhack
}


FILES:${PN} = "/home/felix/opensslhack/my_bad_sqrt \
              "  
