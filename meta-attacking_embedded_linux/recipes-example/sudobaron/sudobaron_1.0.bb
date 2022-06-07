DESCRIPTION = "Sudo Baron Samedit CVE-2021-3156"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hax.c \
           file://lib.c \
           file://brute.sh"
           
RDEPENDS:${PN} = "bash gnuparallel"                   

S = "${WORKDIR}"



do_compile() {
        ${CC} -std=c99 hax.c ${LDFLAGS} -o sudo-hax-me-a-sandwich
        ${CC} -fPIC -shared -o 'P0P_SH3LLZ_ .so.2' ${LDFLAGS} lib.c
        ${CC} -DBRUTE -fPIC -shared  -o 'P0P_SH3LLZ_ .so.2' ${LDFLAGS} lib.c
    
}

do_install() {
        install -d ${D}${bindir}
        mkdir   -p ${D}/home/hansolo/sudobaron/libnss_X
        install -m 0755 sudo-hax-me-a-sandwich ${D}/home/hansolo/sudobaron
        install -m 0755 hax.c ${D}/home/hansolo/sudobaron
        install -m 0755 lib.c ${D}/home/hansolo/sudobaron
        install -m 0755 'P0P_SH3LLZ_.so.2' ${D}/home/hansolo/sudobaron/libnss_X
        install -m 0755 'brute.sh' ${D}/home/hansolo/sudobaron
      
}

FILES:${PN} = "/usr \
               /usr/bin \
	       /home/hansolo/sudobaron/sudo-hax-me-a-sandwich \
	       /home/hansolo/sudobaron/hax.c \
	       /home/hansolo/sudobaron/lib.c \
	       /home/hansolo/sudobaron/libnss_X/P0P_SH3LLZ_.so.2 \
	       /home/hansolo/sudobaron/brute.sh \
	      "
	   

