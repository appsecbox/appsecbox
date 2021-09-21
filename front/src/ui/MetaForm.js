
import {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import Api from "../api/Api";

const MetaForm = (props) => {
    const [key, setKey] = useState('')
    const [value, setValue] = useState('')

    return (
        <div>
            <TextField id="standard-basic" label="Key" variant="standard"
                       onChange={(event) => setKey(event.target.value)} value={key}/>
            <TextField id="standard-basic" label="Value" variant="standard"
                       onChange={(event) => setValue(event.target.value)} value={value}/>
            <Button
                variant={"contained"}
                onClick={() => Api.createMetaForApplication(props.applicationId,key,value).then(() => (window.location.reload()))}>
                Add
            </Button>
        </div>
    )
}

export default MetaForm