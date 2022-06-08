#!/bin/bash
qemu-system-x86_64 \
-device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no \
-object rng-random,filename=/dev/urandom,id=rng0 \
-device virtio-rng-pci,rng=rng0 \
-drive file=images/qemux86-64/core-image-minimal-qemux86-64-20220603112642.rootfs.ext4,if=virtio,format=raw \
-usb -device usb-tablet   \
-cpu IvyBridge -machine q35 -smp 4 -m 256 -serial mon:vc -serial null  \
-kernel images/qemux86-64/bzImage--5.15.22+git0+2d38a472b2_7f685244af-r0-qemux86-64-20220519165043.bin \
-append 'root=/dev/vda rw  mem=256M ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 ' 
