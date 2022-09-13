if  [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    echo 'this script must be sourced, not executed' 2>&1
    exit 1
fi

up_tm() {
    cur=$(pwd -P)
    while [ ! -z $cur ] && [ ! -r "$cur/.tm" ]; do
        cur=${cur%/*}
    done
    if [ -r "$cur/.tm" ]; then
        chmod u+x "$cur/.tm"
        "$cur/.tm" "$@"
    else
        echo "${0##*/}: errore: posizionarsi nella directory di una delle esercitazioni..." 2>&1
        return
    fi
}
export -f up_tm
