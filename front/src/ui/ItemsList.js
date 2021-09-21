import {useEffect, useState} from "react";
import Bage from "./Bage";
import {DataGrid} from "@mui/x-data-grid"

/**
 *
 * @param props = data (array of data) or getDataPromise (promise returning array of data), type
 */
const ItemsList = (props) => {
    const [data, setData] = useState(null)

    useEffect(() => {
        if (props.getDataPromise!=null) {
            if (!data) {
                props.getDataPromise.then((response) => (setData(response)))
            }
        } else {
            setData( props.data )
        }
    }, [data,props.getDataPromise, props.data])

    if (data != null) {

        const columns = []
        const rows = []

        if (data.length > 0) {
            let firstRow = data[0]

            Object.keys(firstRow).forEach(function (k) {
                if (k === "id") {
                } else if (k === "name") {
                    columns.push({
                        field: k, headerName: k, flex: 300, renderCell: (params) => {
                            return <Bage id={params.row.id} type={props.type} name={params.row.name}></Bage>
                        }
                    })
                } else {
                    columns.push({field: k, headerName: k, flex: 300})
                }
            });
        }

        data.forEach((item) => {
            rows.push(item)
        })

        return (
            <div style={{height: 500, width: '100%'}}>
                <DataGrid rows={rows} columns={columns} pageSize={50}>
                </DataGrid>
            </div>
        )
    } else {
        return (<div>Loading...</div>)
    }
}

export default ItemsList