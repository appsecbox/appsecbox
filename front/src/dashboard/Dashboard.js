import {useEffect, useState} from "react";
import Api from "../api/Api";

const Dashboard = (props) => {
    const [stats, setStats] = useState(null)

    useEffect( () => {
        if(!stats){
            Api.getStats().then( (response) => (setStats(response)) )
        }
    }, [stats])

    return (
        <div>Number of applications: {props.applicationsCount}</div>
    )
}

export default Dashboard