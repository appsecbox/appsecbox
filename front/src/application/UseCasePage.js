import {useEffect, useState} from "react";
import Api from "../api/Api";
import TechnicalComponentForm from "../application/TechnicalComponentForm"
import {Card, CardActions, CardContent, CardHeader, Grid} from "@mui/material";
import MetaForm from "../ui/MetaForm";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import {Breadcrumbs, Typography} from "@material-ui/core";
import {Link} from "react-router-dom";
import BadgeList from "../ui/BadgeList";
import DatasetLinkForm from "./DatasetLinkForm";

const UseCasePage = (props) => {
    const [application, setApplication] = useState(null)
    const [useCase, setUseCase] = useState(null)
    const useCaseId = props.match.params.useCaseId
    const applicationId = props.match.params.applicationId

    useEffect(() => {
        if (!application) {
            Api.getApplicationById(applicationId).then((result) => setApplication(result))
        }
        if (!useCase) {
            Api.getUseCaseById(applicationId,useCaseId).then((result) => setUseCase(result))
        }
    }, [application, applicationId,useCase,useCaseId])

    if (useCase != null && application!=null) {

        return (<div>
            <Breadcrumbs aria-label="breadcrumb">
                <Link underline="hover" color="inherit" to={"/application/"+applicationId}>
                    {application.name}
                </Link>
                <Typography color="text.primary">Use-Case: {useCase.name}</Typography>
            </Breadcrumbs>
            <br/>

            <Grid container spacing={3}>
                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"General"} />
                        <CardContent>
                            ID: {useCaseId} <br/>
                            Name: {useCase.name} <br/>
                            Application: {application.name} <br/>
                            Actor: {useCase.actor} <br/>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Action"} />
                        <CardContent>
                            {useCase.action}
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Components"} />
                        <CardContent>
                            <BadgeList mapKey={"id"} mapValue={"name"} type={"application/"+applicationId+"/component"} data={useCase.components}></BadgeList>
                        </CardContent>
                    </Card>
                </Grid>

            </Grid>
        </div>)
    } else {
        return (<div>Loading...</div>)
    }
}

export default UseCasePage;