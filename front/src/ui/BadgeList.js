import {useEffect, useState} from "react";
import Badge from "./Badge";

/**
 *
 * @param props = data (array of data) or getDataPromise (promise returning array of data), type
 */
const BadgeList = (props) => {
    const [data, setData] = useState(null)

    useEffect(() => {
        if (props.getDataPromise) {
            props.getDataPromise.then((response) => (setData(response)))
        } else {
            setData(props.data)
        }
    }, [data, props.getDataPromise, props.data])

    if (data != null) {

        return (
            <div>
                {props.header ? <h4>{props.header}</h4> : ''}
                {data.map( (item) => (<Badge
                    key={item[props.mapKey]}
                    id={item[props.mapKey]}
                    type={props.type}
                    name={item[props.mapValue]} />) )}
            </div>
        )
    } else {
        return (<div>Loading...</div>)
    }
}

export default BadgeList