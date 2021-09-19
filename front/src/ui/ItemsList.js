import {useEffect, useState} from "react";
import Bage from "./Bage";

const ItemsList = (props) => {
    const [data, setData] = useState(null)

    useEffect(() => {
        if (!data) {
            props.getDataHandle.then((response) => (setData(response)))
        }
    }, [data])

    if(data!=null) {
        return (
            <div>
                {data.map( (d) => {
                    return ( <Bage key={d.id} type={"application"} id={d.id} name={d.name}></Bage> )
                } )}
            </div>
        )
    } else {
        return (<div>Loading...</div>)
    }
}

export default ItemsList