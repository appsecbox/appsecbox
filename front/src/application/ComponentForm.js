import {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import Api from "../api/Api";

const ComponentForm = (props) => {
    const [name, setName] = useState('')

    return (
        <div>
            <TextField id="standard-basic" label="Component's name" variant="standard"
                       onChange={(event) => setName(event.target.value)} value={name}/>
            <Button
                variant={"contained"}
                onClick={() => Api.createComponent(props.applicationId,name).then(() => (window.location.reload()))}>
                Add new Component
            </Button>
        </div>
    )
}

export default ComponentForm