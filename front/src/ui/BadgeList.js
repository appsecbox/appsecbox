import {useEffect, useState} from "react";
import Badge from "./Badge";
import {DataGrid} from "@mui/x-data-grid"

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
        const rows = []
        data.forEach((item) => {
            rows.push(<Badge id={item[props.mapKey]} type={props.type} name={item[props.mapValue]}></Badge>)
        })

        return (
            <div>
                {props.header ? <h4>{props.header}</h4> : ''}
                {rows.map( (r) => (r) )}
            </div>
        )
    } else {
        return (<div>Loading...</div>)
    }
}

export default BadgeList