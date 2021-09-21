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
    static async getComponents(applicationId){
        return this.request("GET", "/api/application/"+applicationId+"/component").then( (response) => {return response.json()} )
    }

    static async getApplicationById( id ){
        return this.request("GET", "/api/application/"+id).then( (response) => {return response.json()} )
    }

    static async createApplication(name){
        return this.request("POST", "/api/application", name)
    }
    static async createComponent(applicationId, name){
        return this.request("POST", "/api/application/"+applicationId+"/component", name)
    }
    static async createMetaForApplication(applicationId, key, value){
        let request = {key:key, value:value}
        return this.request("POST", "/api/application/"+applicationId+"/meta", JSON.stringify(request))
    }


}

export default Api;