
import {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import Api from "../api/Api";

const MetaForm = (props) => {
    const [key, setKey] = useState('')
    const [value, setValue] = useState('')

    return (
        <div>
            <TextField size={"small"} id="standard-basic" label="Key" variant="standard"
                       onChange={(event) => setKey(event.target.value)} value={key}/>
            <TextField size={"small"} id="standard-basic" label="Value" variant="standard"
                       onChange={(event) => setValue(event.target.value)} value={value}/>
            <Button
                size={"small"}
                variant={"contained"}
                onClick={() => {
                    if( props.technicalComponentId ){
                        Api.createMetaForTechnicalComponent(props.applicationId,props.componentId,props.technicalComponentId, key, value).then(() => (window.location.reload()))
                    }else if (props.componentId){
                        Api.createMetaForComponent(props.applicationId,props.componentId, key, value).then(() => (window.location.reload()))
                    }else if (props.applicationId){
                        Api.createMetaForApplication(props.applicationId, key, value).then(() => (window.location.reload()))
                    }
                }}>
                Add
            </Button>
        </div>
    )
}

export default MetaForm