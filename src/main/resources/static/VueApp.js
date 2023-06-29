const { createApp } = Vue;

const mainContainer = {
    data(){
        return {
            coins:[]
        }
    },
    mounted(){
        this.coins = [
            {
                name: "BITCOIN",
                quantity: 0.003
            },
            {
                name: "TESTE",
                quantity: 0.004
            }
        ]
    }
}

createApp(mainContainer).mount('#app')