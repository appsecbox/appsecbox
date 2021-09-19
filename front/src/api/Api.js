class Api {

    static async request( method, uri, data){
        return fetch( uri, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: data
        })
    }

    static async getStats(){
        let statsStub = {applicationsCount:100}
        return JSON.stringify(statsStub)
    }


    static async getApplications(){
        return this.request("GET", "/api/application").then( (response) => {return response.json()} )
    }

    static async createApplication(name){
        return this.request("POST", "/api/application", name)
    }

}

export default Api;