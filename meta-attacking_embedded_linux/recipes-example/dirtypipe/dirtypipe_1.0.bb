DESCRIPTION = "Dirtypipe CVE-2022-0847"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://dirtypipe.c \
	   file://dirtypipez.c"

S = "${WORKDIR}"

RDEPENDS:${PN} = "grep"

do_compile() {
        ${CC} dirtypipe.c ${LDFLAGS} -o exploit
        ${CC} dirtypipez.c ${LDFLAGS} -o exploit_bl4sty
        
}

do_install() {
        mkdir   -p ${D}/home/hansolo/dirtypipe
        install -m 0755 exploit ${D}/home/hansolo/dirtypipe
        install -m 0755 exploit_bl4sty ${D}/home/hansolo/dirtypipe
}

FILES:${PN} = "/home/hansolo/dirtypipe/exploit \
	       /home/hansolo/dirtypipe/exploit_bl4sty \
	      "
