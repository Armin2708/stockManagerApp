import {
    Alert,
    AlertIcon,
    Box, Button,
    FormLabel,
    Input, Select,
    Stack,
    Tab,
    TabList,
    TabPanel,
    TabPanels,
    Tabs
} from "@chakra-ui/react";
import {getInsuranceClient, updateInsuranceClient,} from "../services/insuranceClient.js";
import {Form, Formik, useField} from 'formik';
import * as Yup from 'yup';
import {errorNotification, successNotification} from "../services/notification.js";

const MyTextInput = ({label, ...props}) => {

    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const MySelect = ({label, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Select {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const UpdateClientForm = (client) => {
    return(
        <>
            <Formik
                initialValues={client}
                validationSchema={Yup.object({
                    firstName: Yup.string()
                        .max(30, 'Must be 15 characters or less')
                        .required('Required'),
                    lastName: Yup.string()
                        .max(30, 'Must be 15 characters or less')
                        .required('Required'),
                    gender: Yup.string()
                        .oneOf(
                            ['MALE', 'FEMALE'],
                            'Invalid gender'
                        )
                        .required('Required'),
                    birthDate: Yup.string()
                        .max(10, 'Must be 10 characters or less')
                        .required('Required'),
                    phoneNumber: Yup.string()
                        .max(10, 'Must be 10 characters')
                        .required('Required'),
                    country: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    state: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    city: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    postalCode: Yup.number()
                        .required('Required'),
                    street: Yup.string()
                        .max(30, 'Must be less than 30 characters')
                        .required('Required'),
                    ageRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    healthRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    jobRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    livingAreaRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                })}
                onSubmit={(updatedClient, {setSubmitting}) => {
                    setSubmitting(true);
                    console.log(updatedClient)
                    updateInsuranceClient(client.id,updatedClient)
                        .then(res => {
                            console.log(res);
                            successNotification(
                                "client updated",
                                `${updatedClient.firstName} was successfully updated`
                            )
                        }).catch(err => {
                            console.log(err);
                            errorNotification(
                                err.code,
                                err.response.data.message
                            )
                        }).finally(() => {
                            setSubmitting(false);
                        })
                }}
            >
                {({isValid, isSubmitting}) => (
                    <>
                        <Form>
                            <Tabs>
                                <TabList>
                                    <Tab>Personal Infos</Tab>
                                    <Tab>Address</Tab>
                                    <Tab>Risks</Tab>
                                </TabList>

                                <TabPanels>
                                    <TabPanel>
                                        <Stack spacing={"24px"}>
                                            <MyTextInput
                                                label="First Name"
                                                name="firstName"
                                                type="text"
                                                placeholder={client.firstName}
                                            />
                                            <MyTextInput
                                                label="LastName"
                                                name="lastName"
                                                type="text"
                                                placeholder={client.lastName}
                                            />
                                            <MySelect label="Gender" name="gender">
                                                <option value="MALE">Male</option>
                                                <option value="FEMALE">Female</option>
                                            </MySelect>
                                            <MyTextInput
                                                label="Birth Date"
                                                name="birthDate"
                                                type="text"
                                                placeholder={client.birthDate}
                                            />
                                            <MyTextInput
                                                label="Phone Number"
                                                name="phoneNumber"
                                                type="number"
                                                placeholder={client.phoneNumber}
                                            />
                                        </Stack>
                                    </TabPanel>
                                    <TabPanel>
                                        <Stack spacing={"24px"}>
                                            <MyTextInput
                                                    label="Country"
                                                    name="country"
                                                    type="text"
                                                    placeholder={client.country}
                                                />

                                                <MyTextInput
                                                    label="State"
                                                    name="state"
                                                    type="text"
                                                    placeholder={client.state}
                                                />
                                                <MyTextInput
                                                    label="City"
                                                    name="city"
                                                    type="text"
                                                    placeholder={client.city}
                                                />
                                                <MyTextInput
                                                    label="Postal Code"
                                                    name="postalCode"
                                                    type="number"
                                                    placeholder={client.postalCode}
                                                />
                                                <MyTextInput
                                                    label="Street"
                                                    name="street"
                                                    type="text"
                                                    placeholder={client.street}
                                                />

                                            </Stack>
                                    </TabPanel>
                                    <TabPanel>
                                            <Stack spacing={"24px"}>
                                                <MySelect label="Age Risk" name="ageRisk">
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>

                                                <MySelect label="Health Risk" name="healthRisk">
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                                <MySelect label="Job Risk" name="jobRisk">
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                                <MySelect label="Living Area Risk" name="livingAreaRisk">
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                            </Stack>
                                    </TabPanel>
                                </TabPanels>
                                <Button disabled={!isValid || isSubmitting} type="submit">Submit</Button>
                            </Tabs>
                        </Form>
                    </>
                )}
            </Formik>
        </>
    )
}

export default UpdateClientForm;