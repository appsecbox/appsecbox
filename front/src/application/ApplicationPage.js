import {useEffect, useState} from "react";
import Api from "../api/Api";
import ComponentForm from "./ComponentForm";
import {Card, CardContent, Grid} from "@mui/material";
import ItemsList from "../ui/ItemsList";
import MetaForm from "../ui/MetaForm";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';

const ApplicationPage = (props) => {
    const [application, setApplication] = useState(null)
    const id = props.match.params.id

    useEffect(() => {
        if (!application) {
            Api.getApplicationById(id).then((result) => setApplication(result))
        }
    }, [application, id])

    if (application != null) {
        const meta = []
        Object.keys(application.meta).forEach(function (key) {
            meta.push({key: key, value: application.meta[key]});
        })

        return (<div>
            <h1>{application.name}</h1>

            <Grid container spacing={3}>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            ID: {id}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            Meta:
                            {meta.map((s) => {
                                return <div key={s.key}>{s.key} = {s.value} <DeleteForeverIcon color={"primary"} fontSize={"small"}></DeleteForeverIcon></div>
                            })}
                            <MetaForm applicationId={id}></MetaForm>
                        </CardContent>
                    </Card>
                </Grid>0

                <Grid item xs={12}>
                    <ComponentForm applicationId={id}></ComponentForm>
                </Grid>

                <Grid item xs={12}>
                    <ItemsList type={"application/" + id + "/component"} data={application.components}></ItemsList>
                </Grid>

            </Grid>
        </div>)
    } else {
        return (<div>Loading...</div>)
    }
}

export default ApplicationPage;