<template>
  <main>
    <div class="d-flex justify-end my-2">
      <v-btn color="blue" @click="showAddDialog()">
        Add New Patient
      </v-btn>
    </div>
    <div>
      <v-card>
        <v-card-text>
          <v-row>
            <v-col cols="8"></v-col>
            <v-col cols="4"><v-text-field v-model="search" @input="quickSearch" label="Search" variant="solo" /></v-col>
          </v-row>

          <v-table>
            <thead>
              <tr>
                <th class="text-left">
                  PID
                </th>
                <th class="text-left">
                  First Name
                </th>
                <th class="text-left">
                  Last Name
                </th>
                <th class="text-left">
                  Gender
                </th>
                <th class="text-left">
                  Date of Birth
                </th>
                <th class="text-left">
                  Address
                </th>
                <th class="text-left">
                  Suburb
                </th>
                <th class="text-left">
                  State
                </th>
                <th class="text-center">
                  Action
                </th>


              </tr>
            </thead>
            <tbody>
              <tr v-for="item in patients" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.firstName }}</td>
                <td>{{ item.lastName }}</td>
                <td>{{ item.gender }}</td>
                <td>{{ item.dateOfBirth }}</td>
                <td>{{ item.address }}</td>
                <td>{{ item.suburb }}</td>
                <td>{{ item.state }}</td>
                <td><v-btn size="small" @click="showAddDialog(item)">Edit</v-btn> | <v-btn size="small" color="error"
                    @click="confirmDelete(item)">Delete</v-btn></td>
              </tr>
            </tbody>
          </v-table>
          <v-pagination class="my-2" v-model="page" :length="totalPages" @update:model-value="getList" size="small"></v-pagination>
        </v-card-text>
      </v-card>

    </div>

    <v-dialog v-model="dialogAdd.show">
      <v-card>
        <v-card>
          <v-card-title>
            {{ dialogAdd.data.id ? "Edit Patient" : "Add Patient" }}
          </v-card-title>
          <v-card-text class="d-flex flex-column">
            <v-row>
              <v-col cols="6">
                <h3>First Name*</h3>
                <v-text-field v-model="dialogAdd.data.firstName" label="First Name" variant="solo" />
              </v-col>
              <v-col cols="6">
                <h3>Last Name*</h3>
                <v-text-field v-model="dialogAdd.data.lastName" label="Last Name" variant="solo" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="6">
                <h3>Phone*</h3>
                <v-text-field v-model="dialogAdd.data.phone" label="Phone" variant="solo" />
              </v-col>
              <v-col cols="6">
                <h3>Date of Birth*</h3>
                <v-text-field v-model="dialogAdd.data.dateOfBirth" label="Date of Birth" variant="solo" />
              </v-col>
            </v-row>

            <div>
              <h3>Gender</h3>
              <v-select label="Select Gender" v-model="dialogAdd.data.gender" :items="['Male', 'Female', 'Other']"
                variant="solo" />
            </div>

            <div>
              <h3>Address</h3>
              <v-text-field v-model="dialogAdd.data.address" label="Address" variant="solo" />
            </div>

            <div>
              <h3>Suburb</h3>
              <v-text-field v-model="dialogAdd.data.suburb" label="Suburb" variant="solo" />
            </div>

            <v-row>
              <v-col cols="6">
                <h3>State*</h3>
                <v-select label="Select State" v-model="dialogAdd.data.state"
                  :items="['NSW', 'NT', 'QLD', 'SA', 'TAS', 'VIC', 'WA']" variant="solo" />
              </v-col>
              <v-col cols="6">
                <h3>Post Code</h3>
                <v-text-field v-model="dialogAdd.data.postCode" label="Post Code" variant="solo" />
              </v-col>
            </v-row>

          </v-card-text>
          <v-card-actions>
            <v-btn color="blue" @click="save">
              Save
            </v-btn>
            <v-btn @click="closeDialog">
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogConfirmation.show" persistent width="320">
      <v-card class="pb-2" height="auto">
        <v-card-text class="px-4 pt-5 pb-1">
          <v-row>
            <v-col sm="12">
              <div v-html="dialogConfirmation.message" />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions class="text-center pb-6">
          <v-row>
            <v-col sm="12" class="pb-0">
              <div class="button-group">
                <v-btn @click="closeConfirmationDialog()" size="small" class="
                                      my-auto
                                      mt-1
                                  ">Cancel</v-btn>
                <v-btn @click="deleteItem" min-width="75" size="small" color="blue" class="
                                      white--text
                                      my-auto
                                      mt-1
                                      ml-1
                                  ">Confirm</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </main>
</template>

<style scoped>
h3 {
  padding: 8px;
  font-weight: 600;
}
</style>

<script>

export default {
  data() {
    return {
      headers: [
        { text: 'First Name', value: 'firstName' },
        { text: 'Last Name', value: 'lastName' },
        { text: 'Address', value: 'address' },
        { text: 'Suburb', value: 'suburb' },
        { text: 'State', value: 'state' },
        { text: 'Date of Birth', value: 'dateOfBirth' },
        { text: 'Phone', value: 'phone' },
      ],
      search: "",
      page: 1,
      size: 5,
      dir: "ASC",
      sort: "id",
      totalPages: 1,
      patients: [],

      dialogAdd: {
        show: false,
        data: {

        }
      },
      dialogConfirmation: {
        message: "Are you sure you want to delete this item ?",
        id: null,
      }
    }
  },
  mounted() {
    this.getList();
  },
  methods: {
    onClickConfirm() {
      this.$emit("onClickConfirm");
    },
    onClickCancel() {
      this.$emit("onClickCancel");
    },
    showAddDialog(item = {}) {
      this.dialogAdd.data = item;
      this.dialogAdd.show = true;
    },
    closeDialog() {
      this.dialogAdd.show = false;
      this.dialogAdd.data = {};
    },
    confirmDelete() {

    },
    confirmDelete(item) {
      this.dialogConfirmation.id = item.id;
      this.dialogConfirmation.show = true;
    },
    closeConfirmationDialog() {
      this.dialogConfirmation.id = null;
      this.dialogConfirmation.show = false;
    },
    deleteItem() {
      this.$axios.delete("/patient/" + this.dialogConfirmation.id).then(() => {
        this.getList();
        this.closeConfirmationDialog();
      })
    },
    save() {
      this.$axios.post("/patient", this.dialogAdd.data).then(() => {
        this.getList();
        this.closeDialog();
      })
    },
    quickSearch() {
      this.page = 1;
      this.getList();
    },
    getList() {
      this.$axios.post("/patient/list", { search: this.search, page: this.page, size: this.size, dir: this.dir, sort: this.sort }).then((response) => {
        this.patients = response.data.content;
        this.totalPages = response.data.totalPages;
      });
    },
  },
};
</script>