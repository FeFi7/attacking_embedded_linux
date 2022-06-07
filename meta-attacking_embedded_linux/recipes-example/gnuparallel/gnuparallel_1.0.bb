LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://COPYING;md5=189af8afca6d6075ba6c9e0aa8077626"

S = "${WORKDIR}/parallel-20220322"

RDEPENDS:${PN} = "perl perl-modules" 

DEPENDS = ""

inherit autotools gettext texinfo pkgconfig

SRC_URI = "https://ftp.gnu.org/gnu/parallel/parallel-20220322.tar.bz2"  
SRC_URI[sha256sum] = "df93ccf6a9f529ad2126b7042aef0486603e938c77b405939c41702d38a4e6d8"

PACKAGES =+ "${PN}-parallel"

FILES:${PN}-parallel = "${bindir}/parallel"


