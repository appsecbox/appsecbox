import {useEffect, useState} from "react";
import Api from "../api/Api";
import ComponentForm from "./ComponentForm";
import {Card, CardActions, CardContent, CardHeader, Grid} from "@mui/material";
import ItemsList from "../ui/ItemsList";
import MetaForm from "../ui/MetaForm";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import {Breadcrumbs, Typography} from "@material-ui/core";
import UseCaseForm from "../application/UseCaseForm"
import BadgeList from "../ui/BadgeList";

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
            <Breadcrumbs aria-label="breadcrumb">
                <Typography color="text.primary">{application.name}</Typography>
            </Breadcrumbs>
            <br/>

            <Grid container spacing={3}>
                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"General"} />
                        <CardContent>
                            ID: {id} <br/>
                            Name: {application.name} <br/>
                            Number of components: {application.components.length}<br/>
                            Number of use-cases: {application.useCases.length}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Meta"} />
                        <CardContent>
                            {meta.map((s) => {
                                return <div key={s.key}>{s.key} = {s.value} <DeleteForeverIcon color={"primary"} fontSize={"small"}></DeleteForeverIcon></div>
                            })}
                        </CardContent>
                        <CardActions>
                            <MetaForm applicationId={id}></MetaForm>
                        </CardActions>
                    </Card>
                </Grid>

                <Grid item xs={6} mt={5}>
                    <Card>
                        <CardHeader title={"Components"} />
                        <CardContent>
                            <BadgeList mapKey={"id"} mapValue={"name"} type={"application/" + id + "/component"} data={application.components}></BadgeList>
                        </CardContent>
                        <CardActions>
                            <ComponentForm applicationId={id}></ComponentForm>
                        </CardActions>
                    </Card>
                </Grid>

                <Grid item xs={6} mt={5}>
                    <Card>
                        <CardHeader title={"Use-Cases"} />
                        <CardContent>
                            <BadgeList mapKey={"id"} mapValue={"name"} type={"application/" + id + "/use-case"} data={application.useCases}></BadgeList>
                        </CardContent>
                        <CardActions>
                            <UseCaseForm components={application.components} applicationId={id}></UseCaseForm>
                        </CardActions>
                    </Card>
                </Grid>

            </Grid>
        </div>)
    } else {
        return (<div>Loading...</div>)
    }
}

export default ApplicationPage;