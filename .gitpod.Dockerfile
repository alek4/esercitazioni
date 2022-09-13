FROM gitpod/workspace-full

RUN sudo apt-get update \
    && sudo apt-get install -y language-pack-it \
    && sudo rm -rf /var/lib/apt/lists/*

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk install java 17-open"

RUN mkdir -p /home/gitpod/.bashrc.d/ \
    && echo 'unset JAVA_TOOL_OPTIONS' > /home/gitpod/.bashrc.d/00-scythe \
    && echo 'export PATH="$PATH":/workspace/esercitazioni/.bin' >> /home/gitpod/.bashrc.d/00-scythe
