FROM alpine
RUN apk add --no-cache bash openssh git git-bash-completion
RUN git clone https://github.com/magicmonty/bash-git-prompt.git /root/.bash-git-prompt --depth=1
RUN echo -e "source /usr/share/bash-completion/completions/git\nsource /root/.bash-git-prompt/gitprompt.sh" > /root/.bashrc
CMD ["/bin/bash"]
