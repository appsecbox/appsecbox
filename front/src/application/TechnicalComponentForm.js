import {useEffect, useState} from "react";
import {Button, FormControl, InputLabel, MenuItem, Select, TextField} from "@material-ui/core";
import Api from "../api/Api";

const TechnicalComponentForm = (props) => {
    const [uri, setUri] = useState('')
    const [type, setType] = useState('')
    const [types, setTypes] = useState(null)

    useEffect(()=>{
        if(!types){
            Api.getControls().then( (r) => setTypes(r))
        }
    }, [types])

    return (
        <div>
            <TextField size={"small"} id="standard-basic" label="URI" variant="standard"
                       onChange={(event) => setUri(event.target.value)} value={uri}/>
            <FormControl>
                <InputLabel id="demo-simple-select-label">Type</InputLabel>
                <Select
                    size={"small"}
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={type}
                    placeholder={"Select..."}
                    label="Type"
                    onChange={(event)=>setType(event.target.value)}
                >
                    {
                        types===null?
                            '':
                            types.map( (t) => {
                            return (<MenuItem value={t}>{t}</MenuItem>)
                        })
                    }
                </Select>
            </FormControl>
            <Button
                variant={"contained"}
                size={"small"}
                onClick={() => Api.createTechnicalComponent(props.applicationId,props.componentId,uri,type).then(() => (window.location.reload()))}>
                Add new Technical Component
            </Button>
        </div>
    )
}

export default TechnicalComponentForm