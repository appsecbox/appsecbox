import {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import Api from "../api/Api";

const ApplicationForm = (props) => {
    const [name, setName] = useState('')

    return (
        <div>
            <TextField label="App's name" variant="standard"
                       onChange={(event) => setName(event.target.value)} value={name}/>
            <Button
                variant={"contained"}
                onClick={() => Api.createApplication(name).then(() => (window.location.reload()))}>
                Add new application
            </Button>
        </div>
    )
}

export default ApplicationForm