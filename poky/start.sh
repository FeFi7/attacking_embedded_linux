#!/bin/bash
qemu-system-x86_64 \
-object rng-random,filename=/dev/urandom,id=rng0 \
-device virtio-rng-pci,rng=rng0 \
-drive file=images/qemux86-64/core-image-minimal-qemux86-64-20220603112642.rootfs.ext4,if=virtio,format=raw \
-usb -device usb-tablet   \
-cpu IvyBridge -machine q35 -smp 4 -m 256 -serial mon:vc -serial null  \
-kernel images/qemux86-64/bzImage--5.15.22+git0+2d38a472b2_7f685244af-r0-qemux86-64-20220519165043.bin \
-net user,hostfwd=tcp::10022-:22,hostfwd=tcp::8443-:443 \
-net nic \
-append 'root=/dev/vda rw mem=512M ip=:::::eth0:dhcp oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 '
