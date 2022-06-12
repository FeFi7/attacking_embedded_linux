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
